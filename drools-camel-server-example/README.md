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






