import React from "react";
import Typography from "@mui/material/Typography";

const Footer = () => {
  return (
    <div
      style={{
        padding: "1rem",
        backgroundColor: "#333",
        color: "#fff",
        marginTop: "auto",
        textAlign: "center",
      }}
    >
      <Typography variant="body1">
        © 2023 Renos Kerkides. All rights reserved.
      </Typography>
    </div>
  );
};

export default Footer;
