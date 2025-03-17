import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { getProductById } from "../services/api";
import "../style/ProductDetail.css";

function ProductDetail() {
  const {id} = useParams();
  const [product, setProduct] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchProduct = async () => {
      try {
        const token = localStorage.getItem("token");
        const response = await getProductById(id,token);
        setProduct(response);
      } catch (error) {
        setError(error);
        console.error("Error al obtener el producto: ", error);
      } finally{
        setLoading(false);
      }
    };
    fetchProduct();
  },[id]);

  if(loading) return <p>Cargando...</p>;
  if(error) return <p className="error">{error}</p>;
  if(!product) return <p>Producto no encontrado</p>;
  
  return(
    <div className="product-detail">
      <img src={product.imageUrl} alt={product.name} className="product-image"/>
      <h2>{product.name}</h2>
      <p>{product.description}</p>
      <p><strong>Precio: </strong> ${product.price}</p>
      <p><strong>Stock: </strong>{product.stock} unidades</p>
      <button className="add-to-cart">Agregar al carrito</button>
    </div>
  );
}

export default ProductDetail;