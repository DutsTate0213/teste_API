import React, { useState, useEffect } from "react";
import { getDisciplinaById } from "../service/DisciplinaService";

const ViewDisciplinaLogic = ({ disciplinaId }) => {
  const [disciplina, setDisciplina] = useState(null);

  useEffect(() => {
    const fetchDisciplina = async () => {
      try {
        if (disciplinaId && !isNaN(disciplinaId)) {
          const data = await getDisciplinaById(disciplinaId);
          setDisciplina(data);
        }
      } catch (error) {
        console.error("Erro ao carregar dados da disciplina:", error);
      }
    };

    fetchDisciplina();
  }, [disciplinaId]);

  return (
    <div>
      {disciplina ? (
        <div>
          <h1>{disciplina.nome}</h1>
          <p>{disciplina.descricao}</p>
        </div>
      ) : (
        <p>Carregando...</p>
      )}
    </div>
  );
};

export default ViewDisciplinaLogic;
