package com.joseoliveros.messenger.dao.mysql;

import com.joseoliveros.messenger.dao.DaoException;
import com.joseoliveros.messenger.dao.MessageDao;
import com.joseoliveros.messenger.dao.util.MessageDaoUtil;
import com.joseoliveros.messenger.model.Message;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlMessageDao implements MessageDao {

    private String INSERT = "INSERT INTO message(message, author, created) VALUES(?, ?, ?)";
    private String UPDATE = "UPDATE message SET message = ?, author = ? WHERE id = ?";
    private String DELETE = "DELETE FROM message WHERE id = ?";
    private String GETALL = "SELECT * FROM message";
    private String GETONE = "SELECT * FROM message WHERE id = ?";
    
    private Connection connection;
    private DataSource dataSource;
    private PreparedStatement statement = null;

//    public MySqlMessageDao(Connection connection) {
//        this.connection = connection;
//    }
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Message> obtenerTodos() throws DaoException {
        ResultSet rs = null;
        List<Message> messageList;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(GETALL);
            rs = statement.executeQuery();
            messageList = new ArrayList<>();
            while (rs.next()) {
                Message message = MessageDaoUtil.convertir(rs);
                messageList.add(message);
            }
        } catch (SQLException e) {
            throw new DaoException("Error en SQL", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DaoException("Error e SQL", e);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new DaoException("Error e SQL", e);
                }
            }

        }
        return messageList;
    }

    @Override
    public Message obtener(Long id) throws DaoException {
        Message message = null;
        ResultSet rs = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(GETONE);
            statement.setLong(1, id);
            rs = statement.executeQuery();
            if (rs.next()) {
                message = MessageDaoUtil.convertir(rs);
            }
        } catch (SQLException e) {
            throw new DaoException("Error en SQL", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DaoException("Error e SQL", e);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new DaoException("Error en SQL", e);
                }
            }
        }
        return message;
    }

    @Override
    public Message crear(Message message) throws DaoException {
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, message.getMessage());
            statement.setString(2, message.getAuthor());
            statement.setString(3, message.getCreated().toString());
            if (statement.executeUpdate() == 0) {
                throw new DaoException("Puede que no se haya creado");
            }
            ResultSet rs = statement.getGeneratedKeys();
            Integer key = null;
            if (rs.next()) {
                key = rs.getInt(1);
            }
            message.setId(Long.valueOf(key));
            rs.close();
        } catch (SQLException e) {
            throw new DaoException("Error en SQL", e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new DaoException("Error en SQL", e);
                }
            }
        }
        return message;
    }

    @Override
    public Message actualizar(Long id, Message message) throws DaoException {
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(UPDATE);
            statement.setString(1, message.getMessage());
            statement.setString(2, message.getAuthor());
            statement.setLong(3, id);
            if (statement.executeUpdate() == 0) {
                throw new DaoException("Puede que no se haya actualizado");
            }
        } catch (SQLException e) {
            throw new DaoException("Error en SQL", e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new DaoException("Error en SQL", e);
                }
            }
        }
        return obtener(id);
    }

    @Override
    public boolean eliminar(Long id) throws DaoException {
        boolean eliminated = true;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(DELETE);
            statement.setLong(1, id);
            if (statement.executeUpdate() == 0) {
                eliminated = false;
            }
        } catch (SQLException e) {
            throw new DaoException("Error en SQL", e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new DaoException("Error en SQL", e);
                }
            }
        }
        return eliminated;
    }
}
