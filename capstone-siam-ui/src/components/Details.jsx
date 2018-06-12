import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';


class Details extends Component{
	constructor(){
		super();
		this.state = {
			details: {}
		}
	}

	componentDidMount() { 
		const id = this.props.match.params.id; 
	
		axios.get(`http://35.237.107.174:8080/data/${id}`)
		.then((res) => {
			this.setState({
				details: res.data
			}); 
			console.log(this.state.details)

		})
		.catch((error)=>{
			console.log(error); 
		})
	}

	render(){
		const view = this.state.details
		return(
			<div>
				ID: {view.id}<br/>
				MAC ADDRESS: {view.macaddr}<br/>
				IP ADDRESS: {view.ipaddr}<br/>
				COMPANY: {view.company}<br/>
				TYPE: {view.type}<br/>
				SPEED: {view.rtt}ms per packet
			</div>
		)
	}

}



export default Details; 