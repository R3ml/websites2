import React, {Component} from "react";
import ProductService from "../../service/ProductService";

class ProductList extends Component {
    constructor(props) {
        super(props);

        this.getProducts = this.getProducts.bind(this);

        this.state = {
            products: [],
        }
    }

    //Method for retrieving all products
    getProducts() {
        ProductService.getAll()
            .then(response => {
                this.setState({
                    products: response.data
                });
                console.log(response.data); //for testing
            })
            .catch(e => {
                console.log(e);
            })
    }

    componentDidMount() {
        this.getProducts();
    }

    render() {
        const products = this.state.products;
        const productsRender = products.map((product) => {
            return <li className="media d-flex justify-content-center">
                <img className="mr-3" src={product.imgUrl} alt="Product image"/>
                <div className="media-body">
                    <h5 className="mt-0 mb-1">{product.name}</h5>
                    {product.price}dkk Category: {product.type}
                </div>
            </li>
        })
        return (
            <ul className="list-unstyled">
                {productsRender}
            </ul>
        )
    }
}

export default ProductList;
