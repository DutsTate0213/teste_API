import { useState, useEffect } from "react";
import { useToast } from "@chakra-ui/react";
import { postTurma } from "../../../service/TurmaService";
import { getCurso } from "../../../service/CursoService";

const useFormTurmaLogic = () => {
  const toast = useToast();
  const [nome, setNome] = useState("");
  const [curso, setCurso] = useState("");
  const [periodo, setPeriodo] = useState("");
  const [cursos, setCursos] = useState([]);

  useEffect(() => {
    const fetchCursos = async () => {
      try {
        const response = await getCurso();
        setCursos(response);
      } catch (error) {
        toast({
          title: "Erro",
          description: "Erro ao carregar cursos",
          status: "error",
          duration: 3000,
          isClosable: true,
          position: "top-right",
        });
      }
    };

    fetchCursos();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    try {
      const turmaData = {
        nome,
        cursoId: parseInt(curso),
        periodo: parseInt(periodo),
      };

      await postTurma(turmaData);
      
      toast({
        title: "Sucesso",
        description: "Turma cadastrada com sucesso",
        status: "success",
        duration: 3000,
        isClosable: true,
        position: "top-right",
      });

      setNome("");
      setCurso("");
      setPeriodo("");
    } catch (error) {
      toast({
        title: "Erro",
        description: "Erro ao cadastrar turma",
        status: "error",
        duration: 3000,
        isClosable: true,
        position: "top-right",
      });
    }
  };

  return {
    nome,
    setNome,
    curso,
    setCurso,
    periodo,
    setPeriodo,
    cursos,
    handleSubmit,
  };
};

export default useFormTurmaLogic; 