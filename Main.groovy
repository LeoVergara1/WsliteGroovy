
File file = new File("base64.txt")
String transaccion = file.text
def result = NetworkService.buildRequest("https://www.nomipay.com/demo/integrador_wsdl.php"){
  endpointUrl ""
  method HTTPMethod.POST
  callback {
    urlenc transaccion: transaccion
  }
}.execute().xml

println result