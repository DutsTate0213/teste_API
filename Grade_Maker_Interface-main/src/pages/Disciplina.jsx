import React, { useEffect, useState } from "react";
import "../styles/global.css";
import { Heading } from "@chakra-ui/react";
import { getDisciplina } from "../service/DisciplinaService";

const DisciplinaPage = () => {
  const [DisciplinaPage, setDisciplinas] = useState([]);

  useEffect(() => {
    const fetchDisciplina = async () => {
        const resultado = await getDisciplina();
        console.log(resultado)
    
    };
    //fetchDisciplina()
  });

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
          Disciplina
        </Heading>
      </div>
    </div>
  );
};

export default DisciplinaPage;
