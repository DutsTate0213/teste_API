import Api from "./Api";

export const getDisciplina = async () => {
  try {
    const resposta = await Api.get("/disciplina");
    if (resposta.data && Array.isArray(resposta.data)) {
      return resposta.data;
    } else {
      throw new Error("Formato de dados inesperado");
    }
  } catch (erro) {
    console.error("Erro ao buscar as disciplinas:", erro.message);
    throw erro;
  }
};

export const insertDisciplina = async (objectDisciplina) => {
  try {
    const resposta = await Api.post("/disciplina", objectDisciplina);
    return resposta;
  } catch (erro) {
    console.error("Erro ao inserir disciplina:", erro);
    throw erro;
  }
};

export const updateDisciplina = async (id, objectDisciplina) => {
  try {
    const resposta = await Api.put(`/disciplina/${id}`, objectDisciplina);
    return resposta;
  } catch (erro) {
    console.error("Erro ao atualizar disciplina:", erro);
    throw erro;
  }
};

export const deleteDisciplina = async (id) => {
  try {
    const resposta = await Api.delete(`/disciplina/${id}`);
    return resposta;
  } catch (erro) {
    console.error("Erro ao excluir disciplina:", erro);
    throw erro;
  }
};

export const getProfessorDisciplina = async (id) => {
  try {
    const resposta = await Api.get(`/professor-disciplina/${id}`);
    return resposta;
  } catch (erro) {
    console.error("Erro ao buscar professor disciplina:", erro);
    throw erro;
  }
};