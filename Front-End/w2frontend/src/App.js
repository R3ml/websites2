import './App.css';
import React, { Component } from "react";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";

class App extends Component {
  render() {
      console.log(process.env.REACT_APP_NAME);
      return (
          <Router>
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
          </Router>
      );
  }
}

export default App;
