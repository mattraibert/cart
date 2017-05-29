require 'sinatra'
require 'rest-client'
require 'json'
require "sinatra/reloader"
require 'vault'

CLIENT_ID = ENV['GH_BASIC_CLIENT_ID']
CLIENT_SECRET = ENV['GH_BASIC_SECRET_ID']
Vault.address = "http://127.0.0.1:8200"

get '/' do
  index = <<-INDEX
  <body>
    <p>
      Well, hello there, Matt!
    </p>
    <p>
      <a href="https://github.com/login/oauth/authorize?scope=user:email read:org&client_id=#{CLIENT_ID}">Click here</a> to begin!</a>
    </p>
  </body>
  INDEX
end

get '/callback' do
  session_code = request.env['rack.request.query_hash']['code']

  result = JSON.parse(
      RestClient.post('https://github.com/login/oauth/access_token',
                      {:client_id => CLIENT_ID,
                       :client_secret => CLIENT_SECRET,
                       :code => session_code},
                      :accept => :json)
  )


  gh_token = result['access_token']
  scopes = result['scope'].split(',')

  if gh_token.nil? || !scopes.include?('read:org')
    redirect '/error'
  else
    vault_token = vault_auth(gh_token)
    filename = write_dotenv(vault_token)
    redirect "/success?filename=#{filename}"
  end
end

def vault_auth(gh_token)
  Vault.auth.github(gh_token).auth.client_token
end

def write_dotenv(vault_token)
  filename = ".env.#{Time.now.to_i}"

  dotenv = <<-DOTENV
VAULT_TOKEN=#{vault_token}
  DOTENV

  File.write(filename, dotenv)
  filename
end

if !File.exist?(".env")
  `open http://localhost:4567/`
end

get '/error' do
  "<h1>There was an error</h1>"
end

get '/success' do
  filename = request.env['rack.request.query_hash']['filename']

  success = <<-SUCCESS
  <h1>Success</h1>
  <p>.env was written as #{filename}</p>
  SUCCESS
end
