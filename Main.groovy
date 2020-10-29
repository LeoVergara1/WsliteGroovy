
import groovy.util.XmlSlurper

File file = new File("base64.txt")
String transaccion = file.text
def result = NetworkService.buildRequest("https://www.nomipay.com/demo/integrador_wsdl.php"){
  endpointUrl ""
  method HTTPMethod.POST
  callback {
    urlenc transaccion: transaccion
  }
}.execute().xml
//def result = NetworkService.buildRequest("https://storage.googleapis.com/fintech-crediweb-mx.appspot.com/merged%2F_%2F000100000000_________596322_1.pdf?GoogleAccessId=firebase-adminsdk-cqlgz%40fintech-crediweb-mx.iam.gserviceaccount.com&Expires=1604010662&Signature=EhW5Tgf8MZU0dPQ8kq%2BjNXY4p6TQM2a%2BQkNDQv2hK1dBc8Xdw2FB78PJaWS%2FtN%2FJydnVjIzegm2Tc4%2Fgzykz2D3ICh0eqBXPbyqhaMfWu319rDvuTDN3GaBzfVasizjrwBSSaR7VSdqepK029%2Fb6FB9OCR1Lj2ruk%2FMmJ7GhwGZbuHBPGlK3NzGnluaG6DsZ8owNpwNaoZ570K%2Fel9THnhh10sVXlwRABJbY7TCVGaSRpkk08Wu07POj70rh%2BRmNokJrpedmpuV05%2BJDtHSEE40zxVwg0EhDIOV%2BpP6kkZsRi49Ykjouo6tQO4kDPAxGqmTYkMUX1IWSLfXBn%2BECsg%3D%3D"){
//  endpointUrl ""
//  method HTTPMethod.POST
//}.execute()

def convertToMapFromXml(nodes) {
    nodes.children().collectEntries {
        [ it.name(), it.childNodes() ? convertToMap(it) : it.text() ]
    }
}

def parser = convertToMap(result)
println parser