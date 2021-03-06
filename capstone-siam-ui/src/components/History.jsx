import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import { Chart, Axis, Series, Tooltip, Cursor, Line, Bar } from "react-charts";
import Home from './Home';

class History extends Component{
	constructor(){
		super(); 
		this.state = {
			historyLog: [],
			graphData: [ 
					   {
					    label: "Connected",
					    data: [{ x: "6/13", y: 0}, { x: "6/14", y: 0}]
					  },
					  {
					    label: "Shutdown",
					    data: [{ x: "6/13", y: 0 }, { x: "6/14", y: 0}]
					  },
					  {
					    label: "Disconnected",
					    data: [{ x: "6/13", y: 0 }, { x: "6/14", y: 0}]
					  }
					]
		}
	}

	componentDidMount() {
		const id = this.props.match.params.id; 

		axios.get(`http://35.237.107.174:8080/history/${id}`)
		.then((res) => {
			console.log("Hi")
			console.log(res.data)
			for(let i=0; i<res.data.length; i++){
					let splitString = res.data[i].split('/');
					console.log(splitString)
					
					for(let j=0; j<splitString.length; j++){

						if(splitString[j] === " Message: SUCCESS " && splitString[4].substring(0, 17) === " Date: Wed Jun 13"){
							let stateCopy = this.state.graphData.slice()
							stateCopy[0].data[0].y++
							this.setState({
								graphData: stateCopy
							})
						} else if(splitString[j] === " Message: IP_DEST_HOST_UNREACHABLE " && splitString[4].substring(0, 17) === " Date: Wed Jun 13"){
							let stateCopy = this.state.graphData.slice()
							stateCopy[2].data[0].y++
							this.setState({
								graphData: stateCopy
							})						
						} else if(splitString[j].includes("Timeout reached after ") && splitString[4].substring(0, 17) === " Date: Wed Jun 13") {
							let stateCopy = this.state.graphData.slice()
							stateCopy[1].data[0].y++
							this.setState({
								graphData: stateCopy
							})
						} else if(splitString[j] === " Message: SUCCESS " && splitString[4].substring(0, 17) === " Date: Thu Jun 14"){
							let stateCopy = this.state.graphData.slice()
							stateCopy[0].data[1].y++
							this.setState({
								graphData: stateCopy
							})
						} else if(splitString[j] === " Message: IP_DEST_HOST_UNREACHABLE " && splitString[4].substring(0, 17) === " Date: Thu Jun 14"){
							let stateCopy = this.state.graphData.slice()
							stateCopy[2].data[1].y++
							this.setState({
								graphData: stateCopy
							})						

						} else if(splitString[j].includes(" Message: Timeout reached after " && splitString[4].substring(0, 17) === " Date: Thu Jun 14")) {

						} else if(splitString[j].includes(" Message: Timeout reached after ") && splitString[4].substring(0, 17) === " Date: Thu Jun 14") {

							let stateCopy = this.state.graphData.slice()
							stateCopy[1].data[1].y++
							this.setState({
								graphData: stateCopy
							})
						}
					}


			}

			this.setState({
				historyLog: res.data

				
			}); 


			console.log("qqqq")
			console.log(this.state.historyLog)
			


	

		})
		.catch((error)=>{
			console.log(error); 
		})	
	}

	
	render(){
		const view = this.state.historyLog;
		const data = this.state.graphData;
		const id = this.props.match.params.id;


		return(
			<div className="text-center">
			<h1>Daily History for Device Number: {id} </h1>
			<br/>
				<div className="col-sm-4">
				</div>	
				<div className="graph col-sm-4">	
							
					<Chart data={data} >
					  <Axis primary type="ordinal" />
					  <Axis type="linear" min={0} max={0} stacked />
					  <Series type={Bar} />
					  <Cursor primary />
					  <Cursor />
					  <Tooltip />

					</Chart>

					<div className = "legend">
						<ul>
							<li> Pings per day </li>
						</ul>
					</div>

					<div className = "leg">
						<ul>
							<li> Days </li>
						</ul>
					</div>
				</div>

				<div className="font col-sm-4 text-left">
					<ul>
						<li>GREEN: CONNECTED</li>
						<li>YELLOW: SHUTDOWN</li>
						<li>RED: DISCONNECTED</li>
						
					</ul>
				</div>	
			</div>
			)

	}
}

export default History; 