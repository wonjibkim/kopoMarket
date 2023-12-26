package kopo.poly;

        import org.apache.catalina.connector.Connector;
        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
        import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
        import org.springframework.context.annotation.Bean;
        import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarketApplication.class, args);
    }


    @Bean

    public ServletWebServerFactory serveltContainer(){

        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();

        tomcat.addAdditionalTomcatConnectors(createStandardConnector());

        return tomcat;

    }



    private Connector createStandardConnector(){

        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");

        connector.setPort(8080);

        return connector;

    }

}


