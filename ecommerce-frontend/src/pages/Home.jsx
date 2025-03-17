import { useEffect, useState } from "react";
import {getProducts} from "../services/api";
import { Link } from "react-router-dom";
import "../style/Home.css";

const Home = () =>{
  const [products, setProducts] = useState([]);

  useEffect(() =>{
    const fetchProducts = async () =>{
      try{
        const token = localStorage.getItem("token");
        const data = await getProducts(token);
        setProducts(data);
      } catch(error){
        console.error("Error al obtener los productos: ", error);
      }
    };
    fetchProducts();
  },[]);

  return(
    <div className="home-container">
      <h1>Productos Disponibles</h1>
      <div className="product-grid">
        {products.map((product) =>(
          <div key={product.id} className="product-card">
            <img src={product.imageurl} alt={product.name} className="product-image"/>
            <h2>{product.name}</h2>
            <p>{product.description}</p>
            <p className="price">${product.price}</p>
            <Link to={`/products/${product.id}`} className="details-button">Ver Detalles</Link>
          </div>
        ))}
      </div>
    </div>
  );
}

export default Home;