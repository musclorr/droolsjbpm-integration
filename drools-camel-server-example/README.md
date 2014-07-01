
https://source.jboss.org/browse/JBossRules/trunk/drools-camel/src/test/resources/org/drools/camel/component/xstream.mvt?r=HEAD for details on the API
https://docs.jboss.org/drools/release/6.0.1.Final/drools-docs/html/ch.commands.html#d0e10913




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
      <insert return-object="false">
          <org.drools.server.Message>
             <text>hello</text>
             <number>12</number>
          </org.drools.server.Message>
      </insert>      
       <fire-all-rules />
       <get-objects out-identifier="daobj" objectFilter="org.drools.server.MatchingRulesFilter" />
    </batch-execution>' | xmllint --format -
	
	
TODO: make that work