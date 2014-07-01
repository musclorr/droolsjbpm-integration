Compile :


Run with:

    mvn clean package -DskipTests
    mvn jetty:run -D skipTests -D jetty.port=12345
    time curl -q  -XPOST -H "Content-Type: text/plain"  http://localhost:12345/drools-camel-server-example/kservice/rest/execute -d '<batch-execution lookup="ksession1">
      <insert out-identifier="message">
          <org.drools.server.Message>
             <text>hello 世界</text>
          </org.drools.server.Message>
       </insert>
    </batch-execution>' | xmllint --format -


Example with the json unmarshalling in camel-server:

	time curl -v -XPOST  -H "Content-Type: text/plain" http://ncel291:12345/drools-camel-server-example/kservice/rest/execute -d '{"batch-execution":
	{"lookup":"ksession1",
	"commands":[{"insert":{"object":{
	"org.drools.server.Message":{"text":"hello 世界"}}}},{"fire-all-rules":""}]}}'


Global variables (see kie:set-global in knowledge service) are:
- instantiated by default in stateful mode
- for stateless mode
the input needs a set-global item:

    &lt;set-global identifier="list" out="true">
    &lt;list />
    &lt;/set-global>
      
Returning Matching rules:

	
	 time curl -q  -XPOST -H "Content-Type: text/plain"  http://ncel291:12345/drools-camel-server-example/kservice/rest/execute -d '<batch-execution lookup="ksession1">
      <insert>
          <org.drools.server.Message>
             <text>hello</text>
             <number>12</number>
          </org.drools.server.Message>
      </insert>
      <insert out-identifier="rules" return-object="true">
         <org.drools.server.MatchingRule/>
      </insert>
      <fire-all-rules/>
    </batch-execution>'
	
	
TODO: make that work