import React from "react";
import {
  Container,
  Typography,
  Grid,
  Card,
  CardActionArea,
  CardMedia,
  CardContent,
  Button,
} from "@mui/material";

const HomePage = () => {
  return (
    <Container>
      {/* Header */}
      <Typography variant="h1" align="center" gutterBottom>
        My Food Recipe Blog
      </Typography>

      {/* Hero Section */}
      <Typography variant="h4" align="center" paragraph>
        Welcome to the ultimate food recipe destination!
      </Typography>

      {/* Recipe Cards */}
      <Grid container spacing={4}>
        {[1, 2, 3, 4, 5, 6, 7, 8, 9].map((item) => (
          <Grid item xs={12} sm={6} md={4} key={item}>
            <Card>
              <CardActionArea>
                <CardMedia
                  component="img"
                  alt="Recipe Image"
                  height="140"
                  image={`https://source.unsplash.com/random?food,${item}`}
                />
                <CardContent>
                  <Typography gutterBottom variant="h5" component="div">
                    Recipe {item}
                  </Typography>
                  <Typography variant="body2" color="text.secondary">
                    This is a sample description for Recipe {item}.
                  </Typography>
                </CardContent>
              </CardActionArea>
              <Button size="small" color="primary">
                Learn More
              </Button>
            </Card>
          </Grid>
        ))}
      </Grid>
    </Container>
  );
};

export default HomePage;
