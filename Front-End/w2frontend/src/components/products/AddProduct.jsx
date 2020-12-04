import React, {Component} from "react";
import ProductService from "../../service/ProductService";

class AddProduct extends Component {
    constructor(props) {
        super(props);
        this.onChangeName = this.onChangeName.bind(this);
        this.onChangePrice = this.onChangePrice.bind(this);
        this.onChangeType = this.onChangeType.bind(this);
        this.onChangeDescription = this.onChangeDescription.bind(this);
        this.onChangeImgUrl = this.onChangeImgUrl.bind(this);
        this.saveProduct = this.saveProduct.bind(this);

        this.state = {
            message: "",
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

    onChangeName(e) {
        let newProductState = this.state.currentProduct;
        newProductState.name = e.target.value;
        this.setState({
            currentProduct : newProductState
        })
    }

    onChangePrice(e) {
        let newProductState = this.state.currentProduct;
        newProductState.price = e.target.value;
        this.setState({
            currentProduct : newProductState
        })
    }

    onChangeType(e) {
        let newProductState = this.state.currentProduct;
        newProductState.type = e.target.value;
        this.setState({
            currentProduct : newProductState
        })
    }

    onChangeDescription(e) {
        let newProductState = this.state.currentProduct;
        newProductState.description = e.target.value;
        this.setState({
            currentProduct : newProductState
        })
    }

    onChangeImgUrl(e) {
        let newProductState = this.state.currentProduct;
        newProductState.imgUrl = e.target.value;
        this.setState({
            currentProduct : newProductState
        })
    }

    saveProduct() {
        let data = this.state.currentProduct;
        console.log(data)
        ProductService
            .create(data)
            .then(() => {
                this.setState({
                    message: "Product successfully added"
                })
                window.scrollTo(0,0);
            })
            .catch((e) => {
                console.log(e);
            })
    }


    render() {
        return (
            <div className="container container-sm">
                {
                    this.state.message !== "" ?
                        (<div className="row-cols-1 pt-3 text-center">
                            <div className="alert alert-danger pt-2" role="alert">
                                {this.state.message}
                            </div>
                        </div>) : (
                            console.log("Not added yet")
                        )
                }
                <div className="row">
                    <div className="col">
                    </div>
                    <div className="col col-lg-8">
                        <form onSubmit={(e => e.preventDefault())}>
                            <div className="form-group">
                                <label htmlFor="product-name">Product name</label>
                                <input className="form-control" type="text"  id="product-name"
                                        onChange={this.onChangeName} required/>
                            </div>
                            <div className="form-group">
                                <label htmlFor="price">Price</label>
                                <input className="form-control" type="number" id="price" placeholder=""
                                        onChange={this.onChangePrice} required/>
                            </div>

                            <div className="form-group">
                                <label htmlFor="type">Product type</label>
                                <select className="form-control" id="type"
                                         onChange={this.onChangeType} required>
                                    <option>Personal</option>
                                    <option>Business</option>
                                    <option>Portfolio</option>
                                    <option>Web shop</option>
                                    <option>Blog</option>
                                    <option>Online newspaper</option>
                                </select>
                            </div>
                            <div className="form-group">
                                <label htmlFor="description">Product description</label>
                                <textarea className="form-control" id="description" rows="15"
                                           onChange={this.onChangeDescription} required>
                                </textarea>
                            </div>

                            <div className="form-group">
                                <label htmlFor="img-url">Image URL</label>
                                <input className="form-control" type="text"  id="img-url"
                                       placeholder="https://"  onChange={this.onChangeImgUrl} />
                            </div>

                            <div className="form-group text-center">
                                <button className="btn btn-dark btn-lg" onClick={this.saveProduct}>Add</button>
                            </div>
                        </form>
                    </div>
                    <div className="col">
                    </div>
                </div>
            </div>
        )
    }
}

export default AddProduct;