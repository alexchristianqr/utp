create or alter procedure uspActualizarPreciosUnitarios
(
	@ajuste money,
	@ProductID int
)
as
begin
	begin transaction
	save transaction puntoSalvado

	declare @factor money
	set @factor = 1.1

	begin try
		update Products set UnitPrice = UnitPrice*@factor*@ajuste
		where ProductID = @ProductID
		
		set @factor = 15/0

		update OrderDetails set UnitPrice = UnitPrice*@factor*@ajuste
		where ProductID = @ProductID

		commit transaction

	end try
	begin catch
		--if @@TRANCOUNT > 0	
			-- begin
				rollback transaction puntoSalvado
			-- end
	end catch

end
go

exec uspActualizarPreciosUnitarios 2,1
go