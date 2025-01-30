import { useEffect, useState } from "react";
import { useToast } from "@chakra-ui/react";
import { insertDisciplina, getDisciplina, updateDisciplina, deleteDisciplina } from "../../../service/DisciplinaService"; 

const useFormDisciplinaLogic = () => {
  const toast = useToast();
  const [selectedDisciplina, setSelectedDisciplina] = useState(null);
  
  const [nome, setNome] = useState("");
  const [curso, setCurso] = useState(null);
  
useEffect(() => {



})

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!nome || !curso){
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



      await insertDisciplina(disciplinaData);
      
      toast({
        title: "Sucesso",
        description: "Disciplina cadastrada com sucesso",
        status: "success",
        duration: 3000,
        isClosable: true,
        position: "top-right",
      });

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
    handleSubmit,
  };
};

export default useFormDisciplinaLogic; 