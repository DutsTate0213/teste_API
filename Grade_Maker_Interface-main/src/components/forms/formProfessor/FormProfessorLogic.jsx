import { useState, useEffect } from 'react';
import { getDispProf } from '../../../service/DisponibilidadeService';
import { getDiaSemana, getTurnos } from '../../../service/ConfiguracaoService';


const useFormProfessorLogic = (professor) => {
  const [disponibilidades, setDisponibilidades] = useState([]);
  const [anoSelecionado, setAnoSelecionado] = useState(new Date().getFullYear());
  const [semestreSelecionado, setSemestreSelecionado] = useState(1);
  const [days, setDays] = useState([]);
  const [turnos, setTurnos] = useState([]);
  const [cursos, setCursos] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        // Buscar dias da semana
        const diasResponse = await getDiaSemana();
        setDays(diasResponse.data || []);


        // Buscar turnos
        const turnosResponse = await getTurnos();
        setTurnos(turnosResponse.data || []);

        // Buscar cursos


        // Buscar disponibilidades do professor
        if (professor?.id) {
          const disponibilidadesResponse = await getDispProf(professor.id);
          const disponibilidadesFiltradas = disponibilidadesResponse.filter(
            disp => disp.ano === parseInt(anoSelecionado) && 
                    disp.semestre === parseInt(semestreSelecionado)
          );
          setDisponibilidades(disponibilidadesFiltradas);
        }
      } catch (error) {
        console.error("Erro ao buscar dados:", error);
      }
    };
    fetchData();
  }, [professor?.id, anoSelecionado, semestreSelecionado]);

  // Obtém disciplinas únicas
  const disciplinasUnicas = [...new Set(disponibilidades.map(d => d.disciplina.nome))];



const useViewProfessorLogic = (professor) => {
  const [disponibilidades, setDisponibilidades] = useState([]);
  const [anoSelecionado, setAnoSelecionado] = useState(new Date().getFullYear());
  const [semestreSelecionado, setSemestreSelecionado] = useState(1);
  const [diasSemana, setDiasSemana] = useState([]);
  const [turnos, setTurnos] = useState([]);

  useEffect(() => {
    const fetchDados = async () => {
      try {
        const [diasResponse, turnosResponse] = await Promise.all([
          getDiaSemana(),
          getTurnos(),
        ]);
        setDiasSemana(diasResponse.data);
        setTurnos(turnosResponse.data);
      } catch (error) {
        console.error('Erro ao buscar dados:', error);
      }
    };
    fetchDados();
  }, []);

  useEffect(() => {
    const fetchDisponibilidades = async () => {
      if (!professor) return;
      
      try {
        const response = await getDispProf(professor.id);
        const disponibilidadesFiltradas = response.data.filter(
          (disp) => 
            disp.ano === parseInt(anoSelecionado) && 
            disp.semestre === parseInt(semestreSelecionado)
        );
        setDisponibilidades(disponibilidadesFiltradas);
      } catch (error) {
        console.error('Erro ao buscar disponibilidades:', error);
      }
    };

    fetchDisponibilidades();
  }, [professor, anoSelecionado, semestreSelecionado]);

  // Obtém disciplinas únicas
  const disciplinasUnicas = [...new Set(disponibilidades.map(d => d.disciplina.nome))];

  return {
    disponibilidades,
    anoSelecionado,
    setAnoSelecionado,
    semestreSelecionado,
    setSemestreSelecionado,
    disciplinasUnicas,
    diasSemana,
    turnos,
  };
};




  return {
    disponibilidades,

    anoSelecionado,
    setAnoSelecionado,
    semestreSelecionado,
    setSemestreSelecionado,
    days,
    turnos,
    cursos
  };
};

export default useFormProfessorLogic;
