import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';

class History extends Component{
	constructor(){
		super(); 
		this.state = {
			history: []
		}
	}

	componentDidMount() {
		const id = this.props.match.params.id; 

		axios.get(`http://35.237.107.174:8080/history/${id}`)
		.then((res) => {
			console.log("Hi")
			console.log(res.data)
			this.setState({
				history: res.data
			}); 
			console.log("qqqq")
			console.log(this.state.history)
			//console.log(JSON.stringify(this.state.history))
	

		})
		.catch((error)=>{
			console.log(error); 
		})
		
		
	}
	
	render(){
		const view = this.state.history.map((data, index)=> {
			const info = data.split(' / ')
			console.log(info)
			return(
				<div>			
					{data}
				</div>
			)
		})
		return (
			<div>
				{view}
			</div>
		)
	}
		




}

export default History; 