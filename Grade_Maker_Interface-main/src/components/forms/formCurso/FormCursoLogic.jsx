import { useState } from "react";
import { useToast } from "@chakra-ui/react";
import { insertCurso, getCurso, updateCurso, deleteCurso } from "../../../service/CursoService";

const useFormCursoLogic = () => {
  const toast = useToast();
  const [nome, setNome] = useState("");
  const [turno, setTurno] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    try {
      const cursoData = {
        nome,
        turno,
      };

      await insertCurso(cursoData);
      
      toast({
        title: "Sucesso",
        description: "Curso cadastrado com sucesso",
        status: "success",
        duration: 3000,
        isClosable: true,
        position: "top-right",
      });

      setNome("");
      setTurno("");
    } catch (error) {
      toast({
        title: "Erro",
        description: "Erro ao cadastrar curso",
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
    turno,
    setTurno,
    handleSubmit,
  };
};

export default useFormCursoLogic; 