import ballerina/http;

endpoint http:Listener listener {
    port:4000
};

@http:ServiceConfig { basePath: "/" }
service<http:Service> hello bind listener {

    @http:ResourceConfig { methods: ["GET"], path: "/" }
    sayHello (endpoint caller, http:Request request) {
        http:Response response = new;
        response.setTextPayload("Hello world");

        _ = caller -> respond(response);
    }
}
