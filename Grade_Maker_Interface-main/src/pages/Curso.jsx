import React, { useEffect, useState } from "react";
import "../styles/global.css";
import { Heading, Box, Flex, Text, Circle, Button } from "@chakra-ui/react";
import { getCurso } from "../service/CursoService";

const Curso = () => {
  const [cursos, setCursos] = useState([]);

  useEffect(() => {
    const fetchCurso = async () => {
      setCursos(await getCurso());
    };
    fetchCurso();
  },[]);

  return (
    <div className="page-container">
    <div className="content-wrapper">
        <Heading
          className="page-title"
          as="h2"
          fontSize="4xl"
          position="relative"
          textAlign="center"
          _after={{
            content: '""',
            display: "block",
            width: "100%",
            height: "7px",
            backgroundColor: "purple.500",
            position: "absolute",
            bottom: "-5px",
            left: 0,
          }}
        >
          Curso
        </Heading>
    </div>
</div>
  );
};

export default Curso;
