import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import swal from 'sweetalert2';

class Home extends Component {

	constructor(){
		super(); 
		this.state = {
			device: []
		}
		this.pingInterval = {};
		this.getData = this.getData.bind(this);
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
		this.pingInterval = setInterval(this.getData, 30000);
	}

	getData() {
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

	getCount() {
		let count = 0;
		this.state.device.map((elem, index)=>{
			if(elem.message == "SUCCESS") count++;
		})
		console.log(count)
		return count;
	}

	showData()  {
		return this.state.device.map((elem, index) => 

<<<<<<< HEAD

			<div className = "data col-sm-4" key={index}>
			
=======
			<div className = "data col-sm-12" key={index}>
>>>>>>> 39ac46d5fb32fe1a6c608fce8c65848a130941cd
				<div> 
					<h6> Device Number: {elem.id} 
					<div style = {{float: "right"}}>
							<Link to={`/history/${elem.id}`}>History</Link> 
					 	</div>

						
					</h6>
					<h6> IP Address: {elem.ipAddr}
<<<<<<< HEAD
					</h6> 	

					 <button onClick = { ()=> {this.message(elem.message)}} className = {this.buttonStatus(elem.message)}> Status </button>
=======
					<div style = {{float: "right"}}>
						{elem.type === "printer" &&
							<img className="icon" src='https://png.icons8.com/metro/1600/print.png' />
						}
						{elem.type === "PC" &&
							<img className="icon" src='https://image.flaticon.com/icons/svg/34/34100.svg' />
						}
						{elem.type === "iphone" && 
							<img className="icon" src='https://image.freepik.com/free-icon/hand-with-a-mobile-phone_318-38051.jpg' />
						}
						{elem.type === "phone" &&
							<img className="icon" src='https://image.freepik.com/free-icon/hand-with-a-mobile-phone_318-38051.jpg' />
						}
					 </div>
						
					</h6> 	
					 	<button onClick = { ()=> { this.message(elem.message) } } className = {this.buttonStatus(elem.message)}> Status 
					 	</button>
>>>>>>> 39ac46d5fb32fe1a6c608fce8c65848a130941cd
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
					'System is not connected',
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

<<<<<<< HEAD
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

=======
>>>>>>> 39ac46d5fb32fe1a6c608fce8c65848a130941cd
	showDetails(id){
		axios.get(`http://35.237.107.174:8080/data/${id}`)
		.then((res) => {
			let details = `<h5> Device Details for IP: ${res.data.ipaddr}<br>
			<br>
			Last Connected: ${res.data.last_connected}<br>
			Mac Address: ${res.data.macaddr}<br> 
			Company: ${res.data.company}<br>
			Type: ${res.data.type}<br>
			Speed: ${res.data.rtt} ms per packet <br> 
			Last Connected Date: ${res.data.last_connected.substring(0,10)}<br></h5>
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
<<<<<<< HEAD
						<h2> Devices - Location SIAM </h2>
=======
						<h2> Shared Spaces Devices </h2>
						<h3> Currently Connected Devices: {this.getCount()}</h3>
						
>>>>>>> 39ac46d5fb32fe1a6c608fce8c65848a130941cd
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