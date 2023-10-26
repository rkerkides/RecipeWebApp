import React from "react";
import { Link } from "react-router-dom";
import AppBar from "@mui/material/AppBar";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";

const Navbar = () => {
  return (
    <AppBar position="static">
      <Toolbar>
        <Typography variant="h6" style={{ flexGrow: 1 }}>
          Blog
        </Typography>
        <Button color="inherit">
          <Link to="/" style={{ textDecoration: "none", color: "white" }}>
            Home
          </Link>
        </Button>
        <Button color="inherit">
          <Link
            to="/ingredients"
            style={{ textDecoration: "none", color: "white" }}
          >
            Ingredients List
          </Link>
        </Button>
        <Button color="inherit">
          <Link
            to="/recipes"
            style={{ textDecoration: "none", color: "white" }}
          >
            Recipes List
          </Link>
        </Button>
        <Button color="inherit">
          <Link
            to="/category/vegan"
            style={{ textDecoration: "none", color: "white" }}
          >
            Vegan Recipes
          </Link>
        </Button>
        <Button color="inherit">
          <Link
            to="/category/gluten-free"
            style={{ textDecoration: "none", color: "white" }}
          >
            Gluten-Free Recipes
          </Link>
        </Button>
      </Toolbar>
    </AppBar>
  );
};

export default Navbar;
