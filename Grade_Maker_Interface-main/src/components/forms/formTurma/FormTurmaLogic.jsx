import { useState, useEffect } from "react";
import { useToast } from "@chakra-ui/react";
import { getCurso } from "../../../service/CursoService";
import { insertMatriz} from "../../../service/MatrizService";
import { 
  insertTurma,
  getMatrizTurma,
  deleteMatrizTurma
} from "../../../service/TurmaService";

const useFormTurmaLogic = () => {
  const toast = useToast();
  const [selectTurma, setSelectTurma] = useState("");
  const [nome, setNome] = useState("");
  const [anoInput, setAnoInput] = useState(ano);
  const [semestreInput, setSemestreInput] = useState("");

  const [selectedCurso, setSelectedCurso] = useState(null);
  const [disponiveis, setDisponiveis] = useState([]);
  const [selecionadas, setSelecionadas] = useState([]);

  const disponiveisFiltrados = disponiveis.filter((disciplina) =>
    disciplina.nome.toLowerCase().includes(searchDisponiveis.toLowerCase())
  );

  const selecionadasFiltradas = selecionadas.filter((disciplina) =>
    disciplina.nome.toLowerCase().includes(searchSelecionadas.toLowerCase())
  );

useEffect(() => {
    const fetchDisponibilidade = async () => {
      try{
        if (!nome || !anoInput || !semestreInput) return;

        const resultado = await getMatrizTurma(selectTurma.id)

      } catch (error) {
        console.error("Erro ao buscar disponibilidade:", error);
        toast({
          title: "Erro",
          description: "Falha ao buscar disponibilidade do professor.",
          status: "error",
          duration: 4000,
          position: "top-right",
          isClosable: true,
        });
      }
    }
})


}
export default useFormTurmaLogic; 