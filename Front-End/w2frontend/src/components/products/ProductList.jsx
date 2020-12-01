import React, {Component} from "react";
import ProductService from "../../service/ProductService";
import { Link } from "react-router-dom";


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
            return <div className="card my-5 list-item mx-auto" style={{width: "35rem"}}>
                    <img className="card-img-top" src={product.imgUrl} alt="Product image" />
                        <div className="card-body text-center">
                            <h4 className="card-title">{product.name}</h4>
                            <h5 style={{fontStyle: "italic"}}>{product.price} kr<br/>Type: {product.type}</h5>
                                <p className="card-text">{product.description}</p>
                            <Link
                                to={"/products/" + product.id}
                                className="btn btn-secondary btn-lg"
                            >
                            Buy
                        </Link>
                        </div>
                </div>

        })
        return (
            <div className="d-flex" >
                <ul className="list-unstyled row">
                    {productsRender}
                </ul>
            </div>)
    }
}

export default ProductList;
