import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom"; // Make sure this line is correct
import Navbar from "./components/Navbar";
import Footer from "./components/Footer";
import HomePage from "./components/HomePage";
import IngredientsList from "./components/Ingredients";
import Recipes from "./components/Recipes";

function App() {
  return (
    <div
      className="App"
      style={{
        display: "flex",
        flexDirection: "column",
        minHeight: "100vh",
        minWidth: "100vw",
      }}
    >
      <BrowserRouter>
        <Navbar />
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/ingredients" element={<IngredientsList />} />
          <Route path="/recipes" element={<Recipes />} />
        </Routes>
      </BrowserRouter>
      <div style={{ flex: 1, width: "100%" }}></div>
      <Footer />
    </div>
  );
}

export default App;
