import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'POST'
        url '/api/users/1/shoppingCartItems'
        body("""
                {
                    "product":{
                        "id": 1
                    },
                    "quantity":1
                }
            """
        )
        headers{
            contentType(applicationJsonUtf8())
        }
    }
    response {
        status 201
    }
}