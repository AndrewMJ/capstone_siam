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

	getCount2() {
		let count = 0;
		this.state.device.map((elem, index)=>{
			if(elem.message == "IP_DEST_HOST_UNREACHABLE") count++;
		})
		console.log(count)
		return count;
	}


	getCount3() {
		let count = 0;
		this.state.device.map((elem, index)=>{
			if(elem.message.includes("Timeout reached after")) count++;
		})
		console.log(count)
		return count;
	}

	getCountT() {
		let count = 0;
		this.state.device.map((elem, index)=>{
			if(elem.message) count++;
		})
		console.log(count)
		return count;
	}

	showData()  {
		return this.state.device.map((elem, index) => 

			<div className = "data col-sm-12" key={index}>
				<div> 
					<h6> Device Number: {elem.id} 
					<div style = {{float: "right"}}>
							<Link to={`/history/${elem.id}`}>History</Link> 
					 	</div>

						
					</h6>
					<h6> IP Address: {elem.ipAddr}
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
					'error'
					)
			}else {
				swal('System is shutdown',
					"",
					'warning')
			}	

	}
	buttonStatus(message){
		const prefix = 'btn btn-sm btn-'
		if(message == "SUCCESS"){
			return prefix + 'success'

		}
		else if(message == "IP_DEST_HOST_UNREACHABLE"){
			return prefix + 'danger'
			
		}
		else{
			return prefix + 'warning'
			
		}
	}

	showDetails(id){
		axios.get(`http://35.237.107.174:8080/data/${id}`)
		.then((res) => {
			let details = `<h5> Last Connected Date: ${res.data.last_connected.substring(0,10)}<br>
			Mac Address: ${res.data.macaddr}<br> 
			Speed: ${res.data.rtt} ms per packet <br>
			Company: ${res.data.company}<br>
			Type: ${res.data.type}<br> 
			</h5>
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
						<h2> Shared Spaces Devices </h2>
					</div>
					<div className = "container">
						<h5 className = "device" > 
							Total Devices: {this.getCountT()} <br/>
							Connected Devices: {this.getCount()} <br/>
							Shutdown Devices: {this.getCount3()} <br/>
							Disconnected Devices: {this.getCount2()}
						</h5>
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