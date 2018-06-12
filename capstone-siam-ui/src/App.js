import React, { Component } from 'react';
//import logo from './logo.svg';
import './App.css';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import Home from './components/Home';
import Details from './components/Details';
import NavBar from './components/NavBar';
import History from './components/History';
import Search from './components/Search'


class App extends Component {
  render() {
    return (
      <Router>
        <div>
        <Route path = "/" component = {NavBar} />
        <Route exact path = "/home" component = {Home} />
        <Route exact path = "/details/:id" component = {Details}/>
        <Route exact path = "/history/:id" component = {History}/>
        <Route exact path = "/search" component = {Search} />
        </div>
      </Router>
    );
  }
}

export default App;
