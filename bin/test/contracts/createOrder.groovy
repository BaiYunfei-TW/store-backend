import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'POST'
        url '/api/users/1/orders'
        body("""
                [{
                    "id":1,
                },
                {
                    "id":2
                }
                ]
            """
        )
        headers{
            contentType(applicationJsonUtf8())
        }
    }
    response {
        status 201
        headers {
            header(location(),"/api/users/1/orders/1")
        }
    }
}
