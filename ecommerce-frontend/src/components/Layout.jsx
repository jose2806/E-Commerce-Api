import { Route, Routes, useLocation } from "react-router-dom";
import Login from "../pages/Login";
import Navbar from "../components/Navbar";
import Register from "../pages/Register";
import Home from "../pages/Home";
import Products from "../pages/Products";
import ProductDetail from "../pages/ProductDetails";
import Cart from "../pages/Cart";
import Checkout from "../pages/Checkout";

function Layout() {
  const location = useLocation();

  return(
    <>
      {location.pathname !== "/login" && location.pathname !== "/" && 
      location.pathname !== "/register" && <Navbar />}
      <Routes>
          <Route path="/" element={<Login/>}/>
          <Route path="/login" element={<Login/>}/>
          <Route path="/register" element={<Register/>}/>
          <Route path="/home" element={<Home/>}/>
          <Route path="/products" element={<Products/>}/>
          <Route path="/products/:id" element={<ProductDetail/>}/>
          <Route path="/cart" element={<Cart/>}/>
          <Route path="/checkout" element={<Checkout/>}/>
      </Routes>
    </>
  );
  
}

export default Layout;