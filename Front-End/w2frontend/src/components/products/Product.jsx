import React, {Component} from "react";

class Product extends Component {
    constructor(props) {
        super(props);

        this.state = {
            currentProduct: {
                id: null,
                name: "",
                price: null,
                type: "",
                description: "",
                imgUrl: "",
            }
        }
    }
}

export default Product;