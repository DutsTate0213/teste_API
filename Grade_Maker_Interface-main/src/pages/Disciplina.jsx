import React, { useEffect, useState } from "react";
import FormDisc from "../components/forms/formDisciplina/FormDisciplina";
import TableDisc from "../components/tables/TableDisc";
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
        <Heading as="h1" className="page-title">
          Disciplina
        </Heading>
      </div>
    </div>
  );
};

export default DisciplinaPage;
