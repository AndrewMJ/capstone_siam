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
					    label: "Success",
					    data: [{ x: "TEST", y: 0}, { x: 2, y: 7}]
					  },
					  {
					    label: "Error",
					    data: [{ x: "TEST", y: 0 }, { x: 2, y: 7}]
					  },
					  {
					    label: "Warning",
					    data: [{ x: "TEST", y: 0 }, { x: 2, y: 9}]
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
					
					for(let j=0; j<splitString.length; j++){

						if(splitString[j] === " Message: SUCCESS "){
							let stateCopy = this.state.graphData.slice()
							stateCopy[0].data[0].y++
							this.setState({
								graphData: stateCopy
							})
						}else if(splitString[j] === " Message: IP_DEST_HOST_UNREACHABLE "){
							let stateCopy = this.state.graphData.slice()
							stateCopy[1].data[0].y++
							this.setState({
								graphData: stateCopy
							})
							
						} else if(splitString[j].includes(" Message: Timeout reached after ")) {
							let stateCopy = this.state.graphData.slice()
							stateCopy[2].data[0].y++
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
		const view = this.state.historyLog
		const data = this.state.graphData

		return(

			<div className="graph">			
			
				<Chart data={data} >
				  <Axis primary type="ordinal" />
				  <Axis type="linear" min={0} max={0} stacked />
				  <Series type={Bar} />
				  <Cursor primary />
				  <Cursor />
				  <Tooltip />

			</Chart>
			</div>

			
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