import { useState } from "react";
import { useToast } from "@chakra-ui/react";
import { postDisciplina } from "../../../service/DisciplinaService";

const useFormDisciplinaLogic = () => {
  const toast = useToast();
  const [nome, setNome] = useState("");
  const [cargaHoraria, setCargaHoraria] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    try {
      const disciplinaData = {
        nome,
        cargaHoraria: parseInt(cargaHoraria),
      };

      await postDisciplina(disciplinaData);
      
      toast({
        title: "Sucesso",
        description: "Disciplina cadastrada com sucesso",
        status: "success",
        duration: 3000,
        isClosable: true,
        position: "top-right",
      });

      setNome("");
      setCargaHoraria("");
    } catch (error) {
      toast({
        title: "Erro",
        description: "Erro ao cadastrar disciplina",
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
    cargaHoraria,
    setCargaHoraria,
    handleSubmit,
  };
};

export default useFormDisciplinaLogic; 