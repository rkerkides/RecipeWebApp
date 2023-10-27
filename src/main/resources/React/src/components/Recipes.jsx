import React, { useState, useEffect } from "react";

const RecipesList = () => {
  const [recipes, setRecipes] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetch("http://localhost:8080/recipes") // Spring Boot API url for recipes
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        return response.json();
      })
      .then((data) => {
        setRecipes(data);
        setLoading(false);
      })
      .catch((error) => {
        setError(error);
        setLoading(false);
      });
  }, []);

  const fetchImage = (id) => {
    return `http://localhost:8080/recipes/image/${id}`;
  };

  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error: {error.message}</p>;

  return (
    <div>
      <h1>Recipes List Page</h1>
      <ul>
        {recipes.map((recipe, index) => (
          <li key={index}>
            {recipe.title} - {recipe.description}
            <img
              src={fetchImage(recipe.id)}
              alt={recipe.title}
              width="50"
              height="50"
            />
          </li>
        ))}
      </ul>
    </div>
  );
};

export default RecipesList;
