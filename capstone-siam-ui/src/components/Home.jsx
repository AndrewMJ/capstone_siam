import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import swal from 'sweetalert2';


class Home extends Component {


	constructor(){
		super(); 
		this.state = {
			device: [],
			details: {}
		}
	}


	componentDidMount() { 
		
		axios.get(`http://35.237.107.174:8080/data/`)
		.then((res) => {
			this.setState({
				device: res.data

			}); 

		})
		.catch((error)=>{
			console.log(error); 
		});

	}



	showData()  {
		return this.state.device.map((elem, index) => 


			<div className = "data col-sm-4" key={index}>
			
				<div> 
					<h6> Device Number: {elem.id} 
					<div style = {{float: "right"}}>
							<Link to={`/history/${elem.id}`}>History</Link> 
					 	</div>

						
					</h6>
					<h6> IP Address: {elem.ipAddr}
					</h6> 	

					 <button onClick = { ()=> {this.message(elem.message)}} className = {this.buttonStatus(elem.message)}> Status </button>
					 <div style = {{float: "right"}}> 
						<button onClick = { ()=> {this.showDetails(elem.id)}} className = "btn btn-sm btn-basic"> Details </button>	
					 	</div>
				</div>
			</div>
		
			)
	}
	message(message){
		console.log(message)
			if(message == "SUCCESS"){
			swal(
	  			'System is up and running',
	  			'',
	  			'success'
				)
			}else if (message == "IP_DEST_HOST_UNREACHABLE"){
				swal(
					'System is not working',
					"",
					'warning'
					)
			}else {
				swal('System is shutdown',
					"",
					'error')
			}	

	}

	buttonStatus(message){
		const prefix = 'btn btn-sm btn-'
		if(message == "SUCCESS"){
			return prefix + 'success'

		}
		else if(message == "IP_DEST_HOST_UNREACHABLE"){
			return prefix + 'warning'
			
		}
		else{
			return prefix + 'danger'
			
		}
	}

	count(message){
		const prefix = "You have "
		const ending = " devices "
		let success = 0
		let shutdown = 0
		let broken = 0
		
		if(message == "SUCCESS"){
			success++
			return prefix + success + ending + "connected"

		}
		else if(message == "IP_DEST_HOST_UNREACHABLE"){
			shutdown++
			return prefix + shutdown + ending + "shutdown"
			
		}
		else{
			broken++
			return prefix + broken + ending + "not working"
			
		}
	}

	// deviceImage(id){
	// 	axios.get(`http://35.237.107.174:8080/data/${id}`)
	// 	.then((res) => {
	// 		if (res.data.type == "printer"){
	// 			console.log(true)
	// 			 this.setState({deviceType:'print'})
	// 		}

	// 	})
	// 	.catch((error)=>{
	// 		console.log(error); 
	// 	})
	// 	return imagePath;
	// }

	showDetails(id){
		axios.get(`http://35.237.107.174:8080/data/${id}`)
		.then((res) => {
			let details = `<h5> Device Details for IP: ${res.data.ipaddr}<br>
			<br>
			Last Connected: ${res.data.last_connected}<br>
			Mac Address: ${res.data.macaddr}<br> 
			Company: ${res.data.company}<br>
			Type: ${res.data.type}<br>
			Speed: ${res.data.rtt} ms per packet <br> </h5>
			`
			swal({
				
				html: details
				}
			)
		})
		.catch((error)=>{
			console.log(error); 
		})


	}






	render(){
		
		return(
			<div>
				<div className = "row">
					<div className = "col-sm-12 text-center">
						<h2> Devices - Location SIAM </h2>
					</div>
				</div>
				<div className = "container">
					<div className = "row">
							{this.showData()}

					</div>
					
				</div>
					
				
				
			</div>
		)
	}

}

export default Home; 