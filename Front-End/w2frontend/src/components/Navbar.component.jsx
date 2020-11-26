import React from "react";
import {Link} from "react-router-dom";

class NavbarComponent extends Component {
    render() {
        return(
            <div className="app">
                <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
                    <a className="navbar-brand" href="#">Websites2</a>

                    <div className="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul className="navbar-nav mr-auto">
                            <li className="nav-item">
                                <Link to={"/products"} className="nav-link">
                                    Products
                                </Link>
                            </li>
                            <li className="nav-item">
                                <Link to={"/customers"} className="nav-link">
                                    Customers
                                </Link>
                            </li>
                            <li className="nav-item">
                                <Link to={"/orders"} className="nav-link">
                                    Orders
                                </Link>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        )
    }
}