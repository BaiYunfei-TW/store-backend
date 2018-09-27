import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'GET'
        url '/api/products'
    }
    response {
        status 200
        body(file('products.json'))
        headers {
            contentType(applicationJsonUtf8())
        }
    }
}
