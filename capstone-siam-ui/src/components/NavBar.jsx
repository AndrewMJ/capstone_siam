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
					<form className="navbar-form navbar-right" role="search">
    					<div className="form-group">
        					<input type="text" class="form-control" placeholder="Search"/>
    					</div>
    					<button type="submit" class="btn btn-default">Submit</button>
					</form>
				</div>
			</nav>
		)
	}
}
export default NavBar;