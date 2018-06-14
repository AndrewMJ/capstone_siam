import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import printer from '../images/printer.jpg'; 
import computer from '../images/computer.jpg'
class Welcome extends Component {
render(){
    return (
        <div>
            <div className = "col-sm-12 text-center">
                <h1> This is your Syntel Infrastructure Alert Management System </h1>
            </div>

            <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
               <ol className="carousel-indicators">
                 <li data-target="#carouselExampleIndicators" data-slide-to="0" className="active"></li>
                 <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                 <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
               </ol>

             <div className="carousel-inner">

               <div className="carousel-item active">
                 <img className="d-block w-100 img" src={computer} alt="First slide"/>
               </div>

               <div className="carousel-item">
                 <img className="d-block w-100 img" src={printer} alt="Second slide"/>
               </div>

               <div className="carousel-item ">
                 <img className="d-block w-100 img" src={computer} alt="Third slide"/>
               </div>

             </div>

             <a className="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
               <span className="carousel-control-prev-icon" aria-hidden="true"></span>
               <span className="sr-only">Previous</span>
             </a>

             <a className="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
               <span className="carousel-control-next-icon" aria-hidden="true"></span>
               <span className="sr-only">Next</span>
             </a>
            </div>
        </div>
        )
    }
}

export default Welcome; 