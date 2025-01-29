import Api from "./Api";

export const getTurno = async () => {
  try {
    const resposta = await Api.get("/turno");
    if (resposta.data && Array.isArray(resposta.data)) {
      return resposta.data;
    } else {
      throw new Error("Formato de dados inesperado");
    }
  } catch (erro) {
    console.error("Erro ao buscar os turnos:", erro.message);
    throw erro;
  }
};

export const updateTurno = async (id, objectTurno) => {
  try {
    const resposta = await Api.put(`/turno/${id}/status`, objectTurno);
    return resposta;
  } catch (erro) {
    console.error("Erro ao atualizar turno:", erro);
    throw erro;
  }
};