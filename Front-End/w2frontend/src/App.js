import './App.css';
import React, { Component } from "react";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";

import Navbar from "./components/Navbar";
import ProductList from "./components/products/ProductList";

class App extends Component {
  render() {
      console.log(process.env.REACT_APP_NAME);
      return (
          <Router>
            <Navbar />
            <div className="container-expand bg-secondary">
                <Switch>
                    <Route
                        exact
                        path={["/", "/products"]}
                        component={ProductList}
                    />
                </Switch>
            </div>
          </Router>
      );
  }
}

export default App;
