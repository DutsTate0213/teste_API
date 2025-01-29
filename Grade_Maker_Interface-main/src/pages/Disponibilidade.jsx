import {
  Box,
  Button,
  Flex,
  Grid,
  Heading,
  Input,
  Text,
  Image,
} from "@chakra-ui/react";
import { useEffect, useState } from "react";
import React from "react";
import { getCursos, getDias, getDispProf, getTurnos } from "../service/DisponibilidadeService";
import { getProfessor } from "../service/ProfessorService";
import FormDisponibilidade from "../components/forms/formDisponibilidade/FormDisponibilidade";

export default function Disponibilidade() {
  const [dias, setDias] = useState([]);
  const [turnos, setTurnos] = useState([]);
  const [anoAtual, setAnoAtual] = useState(new Date().getFullYear());
  const [disponibilidade, setDisponibilidade] = useState([]);
  const [cursos, setCursos] = useState([]);
  const [professores, setProfessores] = useState([]);
  const professor = {
    id: 1,
    nome: "Eliel Silva da Cruz",
  };

  useEffect(() => {
    const fetchDias = async () => {
      try {
        const resultado = await getDias();
        if (resultado?.data) {
          const diasAtivos = resultado.data.filter(dia => dia.ativo === 1);
          console.log('Dias ativos:', diasAtivos);
          setDias(diasAtivos);
        }
      } catch (error) {
        console.error("Erro ao buscar dias:", error);
      }
    };
    fetchDias();
  }, []);

  useEffect(() => {
    const fetchProfessores = async () => {
      try {
        const resultado = await getProfessor();
        setProfessores(resultado);
      } catch (error) {
        console.log(error);
      }
    };
    fetchProfessores();
  }, []);

  useEffect(() => {
    const fetchCursos = async () => {
      try {
        const resultado = await getCursos();
        //console.log(resultado.data)
        setCursos(resultado.data);
      } catch (error) {
        console.log(error);
      }
    };
    fetchCursos();
  }, []);

  useEffect(() => {
    const fetchTurnos = async () => {
      try {
        const resultado = await getTurnos();
        if (resultado?.data) {
          const turnosAtivos = resultado.data.filter(turno => turno.ativo === 1);
          console.log('Turnos ativos:', turnosAtivos);
          setTurnos(turnosAtivos);
        }
      } catch (error) {
        console.error("Erro ao buscar turnos:", error);
      }
    };
    fetchTurnos();
  }, []);

  useEffect(() => {
    const fetchDisponibilidade = async () => {
      try {
        const resultado = await getDispProf(39);
        setDisponibilidade(resultado.data);
      } catch (error) {
        console.log("Erro inesperado: " + error);
      }
    };
    fetchDisponibilidade();
  }, []);

  const handleFormChange = (field, value) => {
    console.log(`${field}: ${value}`);
  };

  return (
    <div className="page-container">
      <div className="content-wrapper">
        <Heading
          className="page-title"
          as="h2"
          fontSize="2xl"
          position="relative"
          textAlign="center"
          _after={{
            content: '""',
            display: "block",
            width: "100%",
            height: "5px",
            backgroundColor: "purple.500", // Altere a cor para o desejado
            position: "absolute",
            bottom: "-5px", // Ajuste a posição vertical
            left: 0,
          }}
        >
          Disponibilidade do professor
        </Heading>
        <FormDisponibilidade
          ano={anoAtual}
          cursos={cursos}
          days={dias}
          turnos={turnos}
          professores={professores}
        />
      </div>
    </div>
  );
}
