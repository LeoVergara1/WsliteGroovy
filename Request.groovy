// https://mvnrepository.com/artifact/com.github.groovy-wslite/groovy-wslite
@Grapes(
    [
    @Grab(group='com.github.groovy-wslite', module='groovy-wslite', version='1.1.3'),
    @Grab(group='org.slf4j', module='slf4j-api', version='1.7.30')
    ]
)

import wslite.http.HTTPClientException
import groovy.util.logging.Slf4j
import wslite.rest.*

class Request {
  String baseUrl = ""
  String endpointUrl = ""
  Map query = [:]
  Map headers = ["Accept":"*/*", "Content-Type": "application/x-www-form-urlencoded"]
  HTTPMethod method = HTTPMethod.GET
  Closure callback = {}
  Map urlenc = [:]

  Request(String url){
    this.baseUrl = url
  }

  def baseUrl(String url) { this.baseUrl = url; this }
  def endpointUrl(String e) { this.endpointUrl = e; this }
  def query(q) { this.query = q; this }
  def headers(h) { this.headers = h; this }
  def method(m) { this.method = m; this }
  def callback(c) { this.callback = c; this }
  def urlenc(u) { this.urlenc = u; this }

  def execute(){
    try{
      def client = new RESTClient(this.baseUrl)
      def response = client."${this.method.code}"([path:this.endpointUrl, query:this.query,
        headers:this.headers], this.callback)
      response
    }catch (HTTPClientException e) {
      handleError(
        e:e, method:this.method, baseUrl:this.baseUrl, endpoint:this.endpointUrl, query:this.query)
    }
  }

  private def handleError(Map params) {
    println "${params?.e} -- ${params?.e?.message} por ${params?.method}"
    println "Base URl ${params?.baseUrl}"
    println "Endpoint: ${params?.endpoint}"
    println "Query: ${params?.query ?: 'Sin query'}"
  }
}