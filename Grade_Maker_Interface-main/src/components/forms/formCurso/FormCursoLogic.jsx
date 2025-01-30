import { useState } from "react";
import { useToast } from "@chakra-ui/react";
import { insertCurso, getCurso, updateCurso, deleteCurso } from "../../../service/CursoService";

const useFormCursoLogic = () => {
  const toast = useToast();
  const [selectCurso, setSelectCurso] = useState("");
  const [nome, setNome] = useState("");

  useState(() => {

  })

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    if (!nome){
      toast({
        title: "Erro",
        description: "Preencha todos os campos antes de enviar.",
        status: "error",
        duration: 4000,
        position: "top-right",
        isClosable: true,
      });
      return;
    }

    try {
      const cursoData = {
        nome,
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
    handleSubmit,
  };
};

export default useFormCursoLogic; 