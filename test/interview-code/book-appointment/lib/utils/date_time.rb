module Utils
  class DateTime
    def self.is_valid_date?(param)
      begin
        ::DateTime.strptime(param,'%Q')
      rescue ArgumentError
        return false
      end

      true
    end
  end
end
