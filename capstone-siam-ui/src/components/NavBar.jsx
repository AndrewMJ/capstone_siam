import React, {Component} from 'react';
import {Link} from 'react-router-dom';

class NavBar extends Component{
	render(){
		return(
			<nav className="navbar navbar-inverse">
				<div className="container-fluid">
					<div className="navbar-header">
					<Link to="/" className="navbar-brand">Welcome to SIAM</Link>
		
					</div>

					<ul className = "nav navbar-nav">
						<li> <Link to="/home" className="navbar-brand">Home</Link> </li>
					</ul>
					<ul className = "nav navbar-nav navbar-right">
						<li> <h1> Test </h1>
						</li>
					</ul>
				</div>
			</nav>
		)
	}
}
export default NavBar;