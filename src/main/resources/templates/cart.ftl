<h1>Cart!</h1>
<table>
  <tr>
  <#list cart.lineItems as lineItem>
    <td>${product.title}</td>
    <td>${product.description}</td>
    <td>${product.price}</td>
  </#list>
  </tr>
</table>
